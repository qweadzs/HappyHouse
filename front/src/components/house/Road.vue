<template>
  <v-container v-if="roads && roads.length != 0" class="bv-example mt-3 cuty">
    <h4>추천 산책로</h4>
    <h2 v-for="(road, index) in roads" :key="index" :road="road">
      <!--<h4>{{ road.cosName }}</h4>
      <h4>{{ road.degree }}</h4>
      <h4>{{ road.detailCos }}</h4> -->
      <v-row justify="space-around">
        <v-col cols="auto">
          <v-dialog transition="dialog-bottom-transition" max-width="600">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="primary" v-bind="attrs" v-on="on">{{
                road.cosName
              }}</v-btn>
            </template>
            <template v-slot:default="dialog">
              <v-card>
                <v-toolbar color="primary" dark>{{ road.cosName }}</v-toolbar>
                <v-card-text>
                  <div class="text-h4 pa-12">거리 : {{ road.degree }}</div>
                  <div class="text-h4 pa-12">소요시간 : {{ road.times }}</div>
                  <div class="text-h4 pa-12">코스 : {{ road.detailCos }}</div>
                </v-card-text>
                <v-card-actions class="justify-end">
                  <v-btn text @click="dialog.value = false">Close</v-btn>
                </v-card-actions>
              </v-card>
            </template>
          </v-dialog>
        </v-col>
      </v-row>
    </h2>
  </v-container>
  <v-container v-else> </v-container>
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
      // console.log(this.house.도로명시군구코드 + "시군구코드");
      roadList(
        this.house.도로명시군구코드,
        ({ data }) => {
          this.roads = data;
          // console.log(this.roads + "산책길 불러오기");
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
