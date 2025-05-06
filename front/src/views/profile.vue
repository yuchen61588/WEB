<template>
  <div class="profile-container">
    <div class="profile-header">
      <div class="avatar-container">
        <img
          :src="'http://localhost:8080/api/file/' + users.url"
          alt="User avatar"
          class="avatar"
        />
      </div>
      <h1 class="username">{{ user.username }}</h1>
    </div>

    <div class="profile-details">
      <div class="detail-item">
        <div class="detail-label">Email</div>
        <div class="detail-value">{{ user.email }}</div>
      </div>

      <div class="detail-item">
        <div class="detail-label">Balance</div>
        <div class="detail-value">
          {{ formatCurrency(user.balance) }}
        </div>
      </div>

      <div class="detail-item">
        <div class="detail-label">Birthday</div>
        <div class="detail-value">{{ user.birthday }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useStore } from "vuex";
import { computed } from "vue";
import { useResizeObserver } from "@vueuse/core";
// You would typically fetch this data from an API
const store = useStore();
const users = computed(() => store.getters.getUser);
const user = ref({
  username: users.value.username,
  email: users.value.email,
  balance: 1250.75,
  birthday: users.value.birthday,
  // avatarUrl: null // Set to null to show the initials placeholder
  avatarUrl: "avatarUrl",
});

// Function to get initials from username
const getInitials = (username) => {
  if (!username) return "";
  return username
    .split(" ")
    .map((name) => name.charAt(0))
    .join("")
    .toUpperCase();
};

// Format currency with locale
const formatCurrency = (amount) => {
  return new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD",
  }).format(amount);
};

// Format date in a readable format
const formatDate = (dateString) => {
  if (!dateString) return "Not provided";
  const date = new Date(dateString);
  return new Intl.DateTimeFormat("en-US", {
    year: "numeric",
    month: "long",
    day: "numeric",
  }).format(date);
};

// You would typically fetch user data here
onMounted(async () => {
  try {
    // Simulating API call
    // const response = await fetch('/api/user/profile');
    // user.value = await response.json();
    // For demonstration, we're using static data
    // In a real app, you'd load this from your API
  } catch (error) {
    console.error("Failed to load user profile:", error);
  }
});
</script>

<style scoped>
.profile-container {
  max-width: 800px;
  margin: 0 auto;
  margin-top: 20px;
  height: 100vh;
  padding: 2rem;
  background-color: var(--cardbg);
  border-radius: 8px;
  box-shadow: var(--boxshadow);
  color: var(--textprimary);
}

.profile-header {
  display: flex;
  align-items: center;
  margin-bottom: 2rem;
  padding-bottom: 1rem;
  border-bottom: 1px solid var(--bordercolor);
}

.avatar-container {
  margin-right: 1.5rem;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  border: 1px solid var(--bordercolor);
}

.avatar-placeholder {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: var(--textsecondary);
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2rem;
  font-weight: bold;
}

.username {
  font-size: 2rem;
  margin: 0;
  color: var(--textprimary);
}

.profile-details {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.detail-item {
  display: flex;
  flex-direction: column;
  padding: 1rem;
  background-color: var(--cardbg);
  border-radius: 6px;
  border: 1px solid var(--bordercolor);
  transition: all 0.2s ease;
}

.detail-item:hover {
  background-color: var(--buttonhover);
}

.detail-label {
  font-size: 0.9rem;
  color: var(--texttertiary);
  margin-bottom: 0.25rem;
}

.detail-value {
  font-size: 1.2rem;
  color: var(--textsecondary);
  font-weight: 500;
}

/* Balance value color special styling */
.detail-item:nth-child(2) .detail-value {
  color: var(--time-display-color);
}

@media (max-width: 600px) {
  .profile-header {
    flex-direction: column;
    text-align: center;
  }

  .avatar-container {
    margin-right: 0;
    margin-bottom: 1rem;
  }
}
</style>
