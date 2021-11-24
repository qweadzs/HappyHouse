<template>
  <h2>111</h2>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "Road",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  watch: {
    house: function () {},
  },
};
</script>

<style></style>
