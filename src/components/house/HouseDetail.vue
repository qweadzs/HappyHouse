<template>
  <b-container v-if="house" class="bv-example-row">
    <v-row class="mb-2 mt-1">
      <v-col
        ><v-card loading="true">
          <KakaoMap />
        </v-card>
        <v-card class="chart">
          <Chart />
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <!-- <b-alert show variant="danger"
          >거래금액 :
          {{
            (parseInt(house.거래금액.replace(",", "")) * 10000) | price
          }}원</b-alert
        > -->
        <v-card outlined> asdf </v-card>
      </v-col>
    </v-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import KakaoMap from "@/components/house/KakaoMap.vue";
import Chart from "@/components/house/Chart.vue";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  components: {
    KakaoMap,
    Chart,
  },
  data() {
    return {
      chartData: {
        Books: 24,
        Magazine: 30,
        Newspapers: 10,
      },
    };
  },
  computed: {
    ...mapState(houseStore, ["house"]),
    KakaoMap,
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
.chart {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
