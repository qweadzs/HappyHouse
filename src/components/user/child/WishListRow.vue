<template>
  <tr>
    <th>
      <router-link
        :to="{
          name: 'WishView',
          params: {
            no: no,
            aptCode: aptCode,
            aptName: aptName,
            dongCode: dongCode,
            dongName: dongName,
            buildYear: buildYear,
            jibun: jibun,
            dealAmount: dealAmount,
            dealYear: dealYear,
            dealMonth: dealMonth,
            dealDay: dealDay,
            area: area,
          },
        }"
        >{{ aptName }}</router-link
      >
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
  data() {
    return {
      wishhouse: {},
    };
  },
  props: {
    userid: String,
    no: Number,
    aptCode: String,
    aptName: String,
    dongCode: String,
    dongName: String,
    buildYear: Number,
    jibun: String,
    dealAmount: String,
    dealYear: String,
    dealMonth: String,
    dealDay: String,
    area: String,
  },
  computed: {},
  methods: {
    // searchURL() {
    //   window.open(
    //     "https://map.kakao.com/link/search/" +
    //       this.dongName +
    //       " " +
    //       this.aptName
    //   );
    // },
  },
};
</script>

<style scoped></style>
