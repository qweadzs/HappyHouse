<template>
  <v-footer color="primary lighten-1" padless>
    <v-row align="center" justify="space-around">
      <v-text-field
        depressed
        color="primary"
        justify="center"
        v-model="email"
        :rules="emailRules"
        label="E-mail"
        required
      ></v-text-field>
      <v-btn depressed color="primary"> Primary </v-btn>
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
