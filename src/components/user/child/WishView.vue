<template>
  <v-container class="bv-example-row mt-3 text-center">
    <h3>
      <div class="container">
        <div class="masthead-subheading">House Detail</div>
      </div>
    </h3>

    <v-card class="mx-auto" max-width="800">
      <!-- 여기 카카오맵 가능? -->
      <v-img
        class="white--text align-end"
        height="400px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
      </v-img>

      <v-card-subtitle class="pb-0">
        {{ this.aptName }}
      </v-card-subtitle>

      <v-card-text class="text--primary">
        <div>{{ this.dongName }}</div>
        <div>{{ this.buildYear }}년 건축</div>
        <div>{{ this.dealAmount }}천원</div>
        <div>{{ this.dealMonth }}거래년도</div>
        <div>{{ this.dealDay }}거래일</div>
        <div>{{ this.area }}m2</div>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: "WishView",
  data() {
    return {
      no: this.$route.params.no,
      aptCode: this.$route.params.aptCode,
      aptName: this.$route.params.aptName,
      dongCode: this.$route.params.dongCode,
      dongName: this.$route.params.dongName,
      buildYear: this.$route.params.buildYear,
      jibun: this.$route.params.jibun,
      dealAmount: this.$route.params.dealAmount,
      dealYear: this.$route.params.dealYear,
      dealMonth: this.$route.params.dealMonth,
      dealDay: this.$route.params.dealDay,
      area: this.$route.params.area,
    };
  },
  created() {
    console.log(this.$route.params.no);
  },
};
</script>

<style></style>
