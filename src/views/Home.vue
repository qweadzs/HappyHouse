<template>
  <v-container class="bv-example-row mt-2 text-center">
    <v-carousel>
      <v-carousel-item
        v-for="(item, i) in items"
        :key="i"
        :src="item.src"
        reverse-transition="fade-transition"
      >
      </v-carousel-item>
    </v-carousel>
    <div class="title">니가산은(=)그집</div>
  </v-container>
</template>

<script>
export default {
  name: "Main",
  props: {
    msg: String,
  },
  data() {
    return {
      items: [
        {
          src: "https://cdn.pixabay.com/photo/2016/11/18/17/20/living-room-1835923_960_720.jpg",
        },
        {
          src: "https://cdn.pixabay.com/photo/2014/08/11/21/39/wall-416060_960_720.jpg",
        },
        {
          src: "https://cdn.pixabay.com/photo/2016/06/24/10/47/house-1477041_960_720.jpg",
        },
        {
          src: "https://cdn.pixabay.com/photo/2017/08/27/10/16/interior-2685521_960_720.jpg",
        },
      ],
    };
  },
};
</script>

<style scoped>
@import url(https://fonts.googleapis.com.com/earlyaccess/nanumgothic.css);
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.title {
  font-weight: 300;
  font-family: serif;
  font-size: 40px;
}
</style>
