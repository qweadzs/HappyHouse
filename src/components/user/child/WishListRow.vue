<template>
  <tr @click="searchURL">
    <th>
      {{ aptName }}
    </th>
    <br />
    <td>주소 : {{ dongName }}</td>
    <td>{{ buildYear }}</td>
    <td>{{ jibun }}</td>
    <p />
    <v-divider inset></v-divider>
  </tr>
</template>

<script>
export default {
  name: "WishListRow",
  props: {
    userid: String,
    aptCode: String,
    aptName: String,
    // dongCode: Number,
    dongName: String,
    buildYear: Number,
    jibun: String,
    lat: String,
    lng: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
  methods: {
    searchURL() {
      window.open(
        "https://map.kakao.com/link/search/" +
          this.dongName +
          " " +
          this.aptName
      );
    },
  },
};
</script>

<style scoped></style>
