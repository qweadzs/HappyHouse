<template>
  <div>
    <h2>
      <div
        class="masthead-subheading mt-3"
        style="text-shadow: 2px 2px 2px gray"
      >
        Info.
      </div>
    </h2>
    <v-card>
      <div class="info-message">최신 아파트 매물을 한 눈에 확인해보세요.</div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "Info",
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");
.info-message {
  font-size: 30px;
  font-family: "Noto Sans KR", sans-serif;
}
</style>
