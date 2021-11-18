<template>
  <v-footer absolute class="overflow-hidden" justify-center>
    <div class="text-center mb-8">
      <v-btn color="deep-purple" outlined @click="active = !active">
        More
      </v-btn>
    </div>

    <v-bottom-navigation v-model="value" :input-value="active" color="indigo">
      <v-btn>
        <span>커피 사주기</span>
        <v-icon>mdi-coffee</v-icon>
      </v-btn>

      <v-btn>
        <span>구독하기</span>
        <v-icon>mdi-heart</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    links: ["Home", "About Us", "Team", "Services", "Blog", "Contact Us"],
    value: 1,
    active: false,
  }),
  name: "FooterBar",
};
</script>
