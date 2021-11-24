<template>
  <v-container v-if="roads && roads.length != 0" class="bv-example mt-3 cuty">
    <h2 v-for="(road, index) in roads" :key="index" :road="road">
      {{ road.cosName }}
    </h2>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import { roadList } from "@/api/house.js";
const houseStore = "houseStore";

export default {
  name: "Road",
  data() {
    return {
      no: Number,
      roads: {
        cosNum: "",
        cosCategory: "",
        divideCode: "",
        divide: "",
        sido: "",
        degree: "",
        times: "",
        cosLevel: "",
        hit: "",
        subway: "",
        transport: "",
        explains: "",
        pdf: "",
        cosName: "",
        spendTime: "",
        detailCos: "",
        pointNum: "",
        pointName: "",
        xPoint: "",
        yPoint: "",
        pointDescript: "",
      },
    };
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  watch: {
    house: function () {
      console.log(this.house.도로명시군구코드 + "시군구코드");
      roadList(
        this.house.도로명시군구코드,
        ({ data }) => {
          this.roads = data;
          console.log(this.roads + "산책길 불러오기");
        },
        (error) => {
          alert("에러입니다.");
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
