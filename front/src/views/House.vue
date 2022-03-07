<template>
  <v-container class="bv-example-row mt-3 text-center">
    <h3>
      <div class="container">
        <div class="masthead-subheading">House Service</div>
      </div>
    </h3>
    <v-row>
      <v-col>
        <house-search-bar></house-search-bar>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="5">
        <house-detail />
      </v-col>
      <v-col cols="5" align="right" rows="6" class="overflow-hidden">
        <house-list />
      </v-col>
      <v-col cols="2" align="right" rows="6" class="overflow-hidden">
        <Road />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import Road from "@/components/house/Road.vue";

export default {
  name: "House",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    Road,
  },
};
</script>
<style scoped>
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
