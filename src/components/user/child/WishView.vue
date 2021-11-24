<template>
  <v-container class="bv-example-row mt-3 text-center">
    <h3>
      <div class="container">
        <div class="masthead-subheading">House Detail</div>
      </div>
    </h3>

    <v-card class="mx-auto" max-width="800">
      <div id="roadview" style="width: 100%; height: 300px"></div>

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
      windows: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=915cffed372954b7b44804ed422b9cf0&libraries=services";
      //autoload=false&
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      var position = new kakao.maps.LatLng(33.450701, 126.570667);

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
  },
};
</script>

<style></style>
