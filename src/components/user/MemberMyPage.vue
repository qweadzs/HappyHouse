<template>
  <!-- <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.username
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button
            variant="primary"
            @click="moveUpdate(userInfo.userid)"
            class="mr-1"
            >정보수정</b-button
          >
          <b-button variant="danger" @click="removeUser(userInfo.userid)"
            >회원탈퇴</b-button
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container> -->

  <v-row justify="center">
    <v-col cols="12" sm="8">
      <v-card>
        <v-card-title class="cyan darken-1">
          <span class="text-h5 white--text"
            >{{ userInfo.userid }}님의 회원 정보</span
          >
          <v-btn
            :disabled="loading"
            class="ma-1"
            color="grey"
            plain
            @click="moveUpdate(userInfo.userid)"
          >
            수정하기
          </v-btn>

          <v-btn
            click="removeUser(userInfo.userid)"
            :loading="loading"
            class="ma-1"
            color="error"
            plain
            @click="removeUser(userInfo.userid)"
          >
            회원탈퇴
          </v-btn>
          <v-spacer></v-spacer>
        </v-card-title>

        <v-list>
          <v-divider inset></v-divider>

          <v-list-item>
            <v-list-item-action>
              <v-icon> mdi-account</v-icon>
            </v-list-item-action>

            <v-list-item-content>
              <v-list-item-title>{{ userInfo.username }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider inset></v-divider>

          <v-list-item>
            <v-list-item-action>
              <v-icon>mdi-email</v-icon>
            </v-list-item-action>

            <v-list-item-content>
              <v-list-item-title>{{ userInfo.email }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider inset></v-divider>

          <v-list-item>
            <v-list-item-action>
              <v-icon>mdi-map-marker</v-icon>
            </v-list-item-action>

            <v-list-item-content>
              <v-list-item-title>{{ userInfo.joindate }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <v-img
          src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"
          height="500px"
        ></v-img>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { deleteUser } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    removeUser(data) {
      if (confirm("정말로 삭제?")) {
        console.log(data);
        deleteUser(data, () => {
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          alert("삭제가 완료되었습니다.");
          if (this.$route.path != "/") this.$router.push({ name: "Home" });
        });
      }
    },
    moveUpdate(data) {
      if (this.$route.path != "/")
        this.$router.push({
          name: "Update",
          params: { userid: data },
        });
    },
  },
};
</script>

<style></style>
