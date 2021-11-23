<template>
  <div outlined>
    <h2>
      <div
        class="masthead-subheading mt-3"
        style="text-shadow: 2px 2px 2px gray"
      >
        부동산 뉴스
      </div>
    </h2>

    <ul>
      <a
        v-for="(news, index) in newsList"
        :key="index"
        v-bind="news"
        v-bind:href="news.newscontent"
      >
        {{ news.newstitle }}<br />
      </a>
    </ul>
  </div>
</template>

<script>
import { newsList } from "@/api/house.js";
export default {
  name: "HotDeal",
  data() {
    return {
      newsList: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    newsList(
      param,
      (response) => {
        this.newsList = response.data;
        console.log(this.newsList);
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style>
.masthead-subheading {
  text-align: left;
  font-size: 50px;
  font-style: italic;
  line-height: 1.5rem;
  margin-bottom: 25px;
  font-family: "Roboto Slab", -apple-system, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
}
</style>
<style scoped></style>
