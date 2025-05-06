<template>
  <div class="main">
    <div class="left">
      <div class="section-container">
        <h2 class="section-title">欢迎{{ user.username }}</h2>
        <div class="daily-card">
          <div class="card-image">
            <img src="/image/R (1).png" alt="Daily inspiration" />
          </div>
          <div class="card-content">
            <p class="quote-text">
              -根据历史的经验，人类的命运取决于他们的选择。<br />
              -但我选择希望。
            </p>
            <p class="quote-author">《流浪地球2》</p>
          </div>
          <div class="card-footer">
            <div class="service-info">@{{ user.username }}</div>
            <div class="date-box">
              <div class="date">{{ currentDay }}/{{ daysInMonth }}</div>
              <div class="day">{{ dayOfWeek }}.</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="right">
      <div class="user">
        <div class="user_conter">
          <div class="user-conter-left">
            <span>用户：{{ user.username }}</span>
            <br />
            <span>email:{{ user.email }}</span>
          </div>

          <img :src="'http://localhost:8080/api/file/' + user.url" alt="你好" />
        </div>
      </div>

      <h2 class="section-title">日历</h2>
      <div class="calendar-wrapper">
        <!-- 标题和月份切换按钮 -->

        <div class="calendar-header">
          <h2>{{ displayYear }} 年 {{ displayMonth + 1 }} 月</h2>
          <div class="calendar-navigation">
            <button @click="prevMonth">上个月</button>
            <button @click="goToToday">今天</button>
            <button @click="nextMonth">下个月</button>
          </div>
        </div>

        <!-- 日历表格 -->
        <table class="calendar-table">
          <thead>
            <tr>
              <!-- 一周从“周一”到“周日” -->
              <th v-for="(wd, i) in weekdays" :key="i">{{ wd }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(week, wIndex) in calendarRows" :key="wIndex">
              <td
                v-for="(day, dIndex) in week"
                :key="dIndex"
                class="calendar-day"
                :class="{
                  'current-month': day.inCurrentMonth,
                  today: isToday(day.date),
                }"
              >
                {{ day.date.getDate() }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { computed } from "vue";
export default {
  name: "Calendar",
  setup() {
    const store = useStore();
    const user = computed(() => store.getters.getUser);
    return { user };
  },
  data() {
    return {
      // 当前真实日期（用于判断“今天”）
      currentDate: new Date(),

      // 正在显示的“年”和“月”（默认是当前年月）
      displayYear: new Date().getFullYear(),
      displayMonth: new Date().getMonth(), // 0 表示 1 月，11 表示 12 月

      // 星期标题，按「周一到周日」顺序
      weekdays: ["一", "二", "三", "四", "五", "六", "日"],

      // 存放按周切分的日历数据
      calendarRows: [],
    };
  },
  created() {
    this.generateCalendar();
  },
  methods: {
    /**
     * 判断某个日期是否为“今天”
     */
    isToday(date) {
      const today = new Date();
      return (
        date.getDate() === today.getDate() &&
        date.getMonth() === today.getMonth() &&
        date.getFullYear() === today.getFullYear()
      );
    },

    /**
     * 生成当前 displayYear/displayMonth 所对应的日历数据
     * 并填充到 calendarRows (6 行 x 7 列)
     */
    generateCalendar() {
      this.calendarRows = [];

      // 当月第一天和最后一天对象
      const firstDayOfMonth = new Date(this.displayYear, this.displayMonth, 1);
      const lastDayOfMonth = new Date(
        this.displayYear,
        this.displayMonth + 1,
        0
      );

      // 计算当月总天数
      const totalDays = lastDayOfMonth.getDate(); // 比如 31 天、30 天、28 天或 29 天

      // 为了以“周一”为一周开始：
      // JS 的 getDay()：0=周日, 1=周一, ... , 6=周六
      // 先算出当月第一天是周几，如果是周日，就要偏移 6 天；如果是周一，偏移 0 天，等等。
      let offset = firstDayOfMonth.getDay() - 1; // -1 让周一=0, 周日=6
      if (offset < 0) {
        offset = 6; // 周日 => offset=6
      }

      // 用一个临时数组装所有日期，再拆分成 6 行
      const daysArr = [];

      // 1) 填充前置空位（来自上个月的日子）
      for (let i = 0; i < offset; i++) {
        // “1 - (offset - i)” => 往前推几天
        const d = new Date(
          this.displayYear,
          this.displayMonth,
          1 - (offset - i)
        );
        daysArr.push({
          date: d,
          inCurrentMonth: false,
        });
      }

      // 2) 填充当月的天数
      for (let i = 1; i <= totalDays; i++) {
        const d = new Date(this.displayYear, this.displayMonth, i);
        daysArr.push({
          date: d,
          inCurrentMonth: true,
        });
      }

      // 3) 填充后置空位（来自下个月的日子）保证总共 6 行（42 个日期格）
      const remaining = 42 - daysArr.length;
      for (let i = 1; i <= remaining; i++) {
        const d = new Date(this.displayYear, this.displayMonth + 1, i);
        daysArr.push({
          date: d,
          inCurrentMonth: false,
        });
      }

      // 分割成 [ [7天], [7天], ..., [7天] ] 共 6 个子数组
      for (let i = 0; i < 6; i++) {
        this.calendarRows.push(daysArr.slice(i * 7, i * 7 + 7));
      }
    },

    /**
     * 跳转到上一个月
     */
    prevMonth() {
      if (this.displayMonth === 0) {
        this.displayYear -= 1;
        this.displayMonth = 11;
      } else {
        this.displayMonth -= 1;
      }
      this.generateCalendar();
    },

    /**
     * 跳转到下一个月
     */
    nextMonth() {
      if (this.displayMonth === 11) {
        this.displayYear += 1;
        this.displayMonth = 0;
      } else {
        this.displayMonth += 1;
      }
      this.generateCalendar();
    },

    /**
     * 返回“今天”所在的月
     */
    goToToday() {
      const today = new Date();
      this.displayYear = today.getFullYear();
      this.displayMonth = today.getMonth();
      this.generateCalendar();
    },

    // 应用当前主题的 CSS 变量到根元素
    // applyTheme() {
    //   const config = this.themeConfig()[this.theme];
    //   const root = document.documentElement;
    //   Object.entries(config).forEach(([key, value]) => {
    //     root.style.setProperty(`--${key}`, value);
    //   });
    // },
    // // 切换主题（调用 applyTheme 更新变量）
    // switchTheme(newTheme) {
    //   this.theme = newTheme;
    //   this.applyTheme();
    // },
  },
};
</script>
<style scoped>
.main {
  display: flex;
  padding: 30px, 200px;
  justify-content: center;
  align-items: self-end;
}
.calendar-wrapper {
  width: 700px;
  background-color: var(--calendarWrapperBg);
  color: var(--calendarWrapperFont);
  padding: 16px;
  border-radius: 8px;
  border: 0.2px solid rgb(43, 42, 42);
}

/* 头部：标题 + 按钮 */
.calendar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.calendar-header h2 {
  margin: 0;
  font-size: 25px;
}

.calendar-navigation button {
  margin: 0 4px;
  background-color: var(--calendarBtnBg);
  color: var(--calendarBtnFont);
  border: none;
  padding: 8px 15px;
  cursor: pointer;
  border-radius: 4px;
}

.calendar-navigation button:hover {
  background-color: var(--calendarBtnHoverBg);
}

/* 表格样式 */
.calendar-table {
  width: 100%;
  border-collapse: collapse;
}

.calendar-table thead {
  background-color: var(--calendarTheadBg);
  color: var(--calendarTheadFont);
}

.calendar-table th,
.calendar-table td {
  width: calc(100% / 7);
  padding: 15px;
  text-align: center;
  border: 1px solid var(--calendarDayBorder);
}

/* 默认日期格（非本月） */
.calendar-day {
  background-color: var(--calendarDayBg);
  border: 1px solid var(--calendarDayBorder);
  color: var(--calendarDayFont);
  text-align: center;
}

/* 标注当前月的单元格 */
.calendar-day.current-month {
  background-color: var(--calendarDayCurrentBg);
  font-weight: bold;
}

/* 高亮显示“今天” */
.calendar-day.today {
  background-color: var(--calendarTodayBg);
  color: var(--calendarTodayFont);
  border: 2px solid var(--calendarTodayBorder);
}
.section-container {
  display: flex;
  flex-direction: column;
  width: 90%;
}
.section-title {
  font-size: 20px;
  font-weight: 600;
  color: var(--calendarWrapperFont);
  margin-bottom: 16px;
  padding-right: 20px;
  text-align: center;
  border-left: 4px solid var(--section-title-border);
  padding-left: 12px;
  transition: color 0.3s ease, border-left 0.3s ease;
}
.daily-card {
  width: 100%;
  max-width: 400px;
  background-color: var(--card-bg);
  border: 1px solid var(--border-color);
  border-radius: 8px;
  overflow: hidden;
  box-shadow: var(--box-shadow);
  display: flex;
  flex-direction: column;
  margin-bottom: 16px;
  flex-shrink: 0;
  transition: background-color 0.3s ease, border 0.3s ease, box-shadow 0.3s ease;
}
.card-image {
  width: 100%;
  position: relative;
  padding-bottom: 100%; /* 1:1 Aspect Ratio */
  overflow: hidden;
  background-color: #f5f5f5;
}
.card-image img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-bottom: 1px solid var(--border-color);
  transition: border-bottom 0.3s ease;
}
.card-content {
  padding: 24px;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.quote-text {
  font-size: 16px;
  line-height: 1.8;
  color: var(--calendarWrapperFont);
  text-align: center;
  margin-bottom: 16px;
  transition: color 0.3s ease;
}
.quote-author {
  font-size: 14px;
  color: var(--calendarWrapperFont);
  text-align: center;
  transition: color 0.3s ease;
}

.card-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  border-top: 1px solid var(--border-color);
  color: var(--calendarWrapperFont);
  transition: border-top 0.3s ease;
}

.service-info {
  font-size: 12px;
  color: var(--text-tertiary);
  transition: color 0.3s ease;
}

.date-box {
  text-align: right;
  padding: 8px;
  border: 1px solid var(--border-color);
  transition: border 0.3s ease;
}
.date {
  font-size: 16px;
  color: var(--text-primary);
  font-weight: bold;
  transition: color 0.3s ease;
}

.day {
  font-size: 14px;
  color: var(--text-secondary);
  transition: color 0.3s ease;
}
.right {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: self-start;
  padding-left: 70px;
  gap: 10px;
}
.right img {
  display: block;
  height: 120px;
  width: 120px;
  padding: 20px;
  border-radius: 60px;
}
@media (max-width: 800px) {
  .main {
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .right {
    padding: 0;
  }
  .calendar-wrapper {
    width: 100%;
  }
}
@media (max-width: 1400px) {
  .main {
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .right {
    padding: 0;
  }
  .calendar-wrapper {
    width: 100%;
  }
}
.user {
  width: 100%;
}
.user_conter {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
}
.user-conter-left {
  padding: 10px 50px 10px 10px;
  border: 1px solid var(--bordercolor);
  border-radius: 10px;
  color: var(--calendarWrapperFont);
}
</style>
