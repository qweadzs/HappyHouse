<template>
  <tr>
    <th>
      <router-link
        :to="{ name: 'WishView', params: { aptCode: this.aptCode } }"
        >{{ aptName }}</router-link
      >
    </th>
    <td>{{ dongName }}</td>
    <td>{{ buildYear }}</td>
    <td>{{ jibun }}</td>
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
};
</script>

<style></style>
