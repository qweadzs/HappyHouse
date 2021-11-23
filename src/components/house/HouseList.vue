<template>
  <v-container
    v-if="houses && houses.length != 0"
    class="bv-example-row mt-3 cuty"
  >
    <house-list-row
      :class="row"
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </v-container>
  <v-container v-else class="bv-example-row mt-3">
    <v-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </v-row>
  </v-container>
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style scoped>
.cuty {
  /* 전체 페이지 높이에서 80퍼센트 만큼을 차지 */
  height: 80vh;
  overflow-y: scroll;
}
.row :hover {
  background-color: lightblue;
  cursor: pointer;
  transition: 0.5s;
}
</style>
