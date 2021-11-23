<template>
  <div>
    <b-row class="ml-4 mt-2">
      <h1>Developers.</h1>
    </b-row>
    <b-row>
      <b-col cols="6">
        <b-card
          title="Card Title"
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Image"
          img-top
          tag="article"
          class="ml-3 mt-2"
        >
          <b-card-text>
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </b-card-text>

          <b-button href="#" variant="primary">Go somewhere</b-button>
        </b-card>
      </b-col>
      <b-col cols="6">
        <b-card
          title="박현우"
          img-src="C:/Users/qwead/Desktop/SSAFY/취업사진.jpg"
          img-alt="Image"
          img-top
          tag="article"
          class="mr-3 mt-2"
        >
          <b-card-text>
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </b-card-text>

          <b-button href="#" variant="primary">Go somewhere</b-button>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "Developers",
};
</script>

<style></style>
