<template>
  <!-- <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left" @submit="onSubmit">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="이름 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="E-Mail:" label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="user.email"
                required
                placeholder="E-Mail 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="submit"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="moveList"
              >취소</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container> -->
  <v-form
    @submit="onSubmit"
    ref="form"
    class="text-center mt-3"
    style="max-width: 60rem"
    v-model="valid"
    lazy-validation
  >
    <v-row>
      <v-col>
        <v-alert variant="secondary" show><h3>회원가입</h3></v-alert>
      </v-col>
    </v-row>
    <v-form-group label-for="userid">
      <v-text-field
        id="userid"
        v-model="user.userid"
        required
        placeholder="아이디"
        @keyup.enter="confirm"
      ></v-text-field>
    </v-form-group>
    <v-text-field
      id="username"
      v-model="user.username"
      required
      placeholder="이름"
      @keyup.enter="confirm"
    ></v-text-field>

    <v-text-field
      type="password"
      id="userpwd"
      v-model="user.userpwd"
      required
      placeholder="비밀번호"
      @keyup.enter="confirm"
    ></v-text-field>
    <v-text-field
      type="email"
      id="email"
      v-model="user.email"
      required
      placeholder="E-Mail"
      @keyup.enter="confirm"
    ></v-text-field>

    <v-btn
      color="success"
      type="submit"
      variant="success"
      class="m-1"
      @click="movePage"
    >
      회원가입
    </v-btn>

    <v-btn color="error" variant="primary" class="m-1" @click="moveList">
      취소
    </v-btn>
  </v-form>
</template>

<script>
import { signupUser } from "@/api/member";

export default {
  name: "MemberJoin",
  data() {
    return {
      user: {
        userid: null,
        username: null,
        userpwd: null,
        email: null,
        joindate: null,
      },
    };
  },
  // 로그인 되어 있을시

  computed: {},
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.user.userid &&
        ((msg = "아이디 입력해주세요"), (err = false), this.userid == null),
        err &&
          !this.user.username &&
          ((msg = "이름을 입력해주세요"), (err = false), this.username == null);
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호 입력해주세요"), (err = false), (this.userpwd = null)),
        err &&
          !this.user.email &&
          ((msg = "email 입력해주세요"), (err = false), this.email == null);

      if (!err) alert(msg);
      else this.registUser();
    },

    registUser() {
      console.log(this.user.email);
      signupUser(
        {
          userid: this.user.userid,
          username: this.user.username,
          userpwd: this.user.userpwd,
          email: this.user.email,
          joindate: null,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          alert("아이디가 중복됩니다. 다른 아이디를 사용해주세요.");
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style></style>
