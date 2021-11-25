<template>
  <div>
    <v-app-bar color="deep-purple" dark>
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <v-toolbar-title>니가 사는 그 집 </v-toolbar-title>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-btn right color="deep-purple" v-if="userInfo" @click="moveMypage()"
          >{{ userInfo.userid }}님 안녕하세요</v-btn
        >
        <!-- <v-btn v-if="!userInfo" @click="moveLogin()">로그인</v-btn>
        <v-btn v-if="!userInfo" @click="moveSignUp()">회원가입</v-btn> -->
      </v-toolbar-items>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" absolute temporary>
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item router-link :to="{ name: 'Home' }">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="!userInfo" router-link :to="{ name: 'SignUp' }">
            <v-list-item-icon>
              <v-icon>mdi-account-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title>회원 가입</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="!userInfo" router-link :to="{ name: 'SignIn' }">
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title>로그인</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="userInfo" router-link :to="{ name: 'MyPage' }">
            <v-list-item-icon>
              <v-icon>mdi-account-circle</v-icon>
            </v-list-item-icon>
            <v-list-item-title>내 정보</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="userInfo" router-link :to="{ name: 'Board' }">
            <v-list-item-icon>
              <v-icon>mdi-developer-board</v-icon>
            </v-list-item-icon>
            <v-list-item-title>게시판</v-list-item-title>
          </v-list-item>

          <v-list-item router-link :to="{ name: 'House' }">
            <v-list-item-icon>
              <v-icon>mdi-home-city-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title>아파트 조회</v-list-item-title>
          </v-list-item>

          <v-list-item
            v-if="userInfo && userInfo.role === 1"
            router-link
            :to="{ name: 'Mail' }"
          >
            <v-list-item-icon>
              <v-icon>mdi-account-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title>관리자 모드</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="userInfo" @click.prevent="onClickLogout">
            <v-list-item-icon>
              <v-icon>mdi-logout-variant</v-icon>
            </v-list-item-icon>
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <!-- <b-nav-item class="align-self-center"
      ><b-avatar
        variant="primary"
        v-text="userInfo ? userInfo.userid.charAt(0).toUpperCase() : ''"
      ></b-avatar
      >{{ userInfo.username }}({{ userInfo.userid }})님 환영합니다.</b-nav-item
    > -->
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
    moveLogin() {
      this.$router.push({ name: "SignIn" });
    },
    moveSignUp() {
      this.$router.push({ name: "SignUp" });
    },
    moveMypage() {
      this.$router.push({ name: "MyPage" });
    },
  },
  data: () => ({
    drawer: false,
    group: null,
  }),
};
</script>

<style scoped></style>
