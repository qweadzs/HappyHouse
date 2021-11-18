<template>
  <v-footer color="primary lighten-1" padless>
    <v-form v-model="valid">
      <v-container>
        <v-row align="">
          <v-col cols="12" md="4">
            <v-text-field
              v-model="firstname"
              :rules="nameRules"
              :counter="10"
              label="이름"
              required
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="4">
            <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
            ></v-text-field>
          </v-col>
          <v-btn elevation="2">구독하기</v-btn>
        </v-row>
      </v-container>
    </v-form>
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
