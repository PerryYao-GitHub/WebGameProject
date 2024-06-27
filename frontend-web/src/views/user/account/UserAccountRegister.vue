<script setup>
import ContentField from "@/components/ContentField.vue";
</script>

<template>
  <ContentField>
    <h2>User Account Register</h2>
    <div class="row justify-content-md-center">
      <div class="col-3">
        <form>
          <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" placeholder="username, phone number or email address">
            <div class="form-text">We'll never share your email or phone number with anyone else.</div>
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" placeholder="password">
            <div class="form-text">Password must contain 6 characters at least.</div>
          </div>
          <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Check me out</label>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </ContentField>
</template>

<style scoped>
button {
  width: 100%;
}
</style>