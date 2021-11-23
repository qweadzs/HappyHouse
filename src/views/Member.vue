<template>
  <v-container class="bv-example-row mt-3 text-center">
    <h3>
      <div class="container">
        <div class="masthead-subheading">Member Service</div>
      </div>
    </h3>
    <router-view></router-view>
  </v-container>
</template>

<script>
export default {
  name: "Member",
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.masthead-subheading {
  font-size: 50px;
  font-style: italic;
  line-height: 1.5rem;
  margin-bottom: 25px;
  font-family: "Roboto Slab", -apple-system, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
}
</style>
