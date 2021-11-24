<template>
  <v-container v-if="house" class="bv-example-row">
    <v-row class="mb-2 mt-1">
      <v-col><KakaoMap /> </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-alert show variant="danger"
          >거래금액 :
          {{
            (parseInt(house.거래금액.replace(",", "")) * 10000) | price
          }}원</v-alert
        >
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import KakaoMap from "@/components/house/KakaoMap.vue";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  components: {
    KakaoMap,
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

<style></style>
