<template>
  <v-footer color="primary lighten-1" padless>
    <v-row justify="center" no-gutters>
      <v-col class="primary lighten-2 py-4 text-center white--text" cols="12">
        <strong>
          <v-btn :to="{ name: 'Home' }" color="white" text rounded class="my-2">
            Home
          </v-btn>
          <v-btn
            :to="{ name: 'Board' }"
            color="white"
            text
            rounded
            class="my-2"
          >
            게시판
          </v-btn>
          <v-btn
            :to="{ name: 'House' }"
            color="white"
            text
            rounded
            class="my-2"
          >
            아파트정보
          </v-btn>
          <v-btn
            :to="{ name: 'SignUp' }"
            color="white"
            text
            rounded
            class="my-2"
          >
            회원가입
          </v-btn>
          <br />
          {{ new Date().getFullYear() }} — 서울_12반_정광균_박현우
        </strong>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    links: ["Home", "About Us", "Team", "Services", "Blog", "Contact Us"],
  }),
  name: "FooterBar",
};
</script>
