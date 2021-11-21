<template>
  <!-- <b-tr>
    <b-td>{{ articleno }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'BoardView', params: { articleno: this.articleno } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ regtime }}</b-td>
  </b-tr> -->

  <tr>
    <td>{{ articleno }}</td>
    <th>
      <router-link
        :to="{ name: 'BoardView', params: { articleno: this.articleno } }"
        >{{ subject }}</router-link
      >
    </th>
    <td>{{ hit }}</td>
    <td>{{ userid }}</td>
    <td>{{ regtime }}</td>
  </tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "BoardListRow",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>
