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
      <v-list-item-icon class=".float-right" @click="deleteRow">
        <v-icon>mdi-delete</v-icon>
      </v-list-item-icon>
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
import { deleteWish } from "@/api/member.js";
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
    wishapt: Object,
  },
  computed: {},
  methods: {
    deleteRow() {
      console.log(this.no);
      // this.$router.push({ name: "MemberMyPage" });
      deleteWish(
        this.no,
        (response) => {
          console.log(response);
          alert("삭제 되었습니다.");
          this.$router.go();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped></style>
