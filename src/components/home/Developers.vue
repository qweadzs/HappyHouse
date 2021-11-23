<template>
  <div>
    <b-row class="ml-4 mt-2">
      <h1>Developers.</h1>
    </b-row>
    <b-row>
      <b-col cols="6">
        <b-card
          class="dev_img1"
          title="정광균"
          img-src="https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F2c17907a-c1d0-47e6-a8c4-c3fb331cfafd%2FKakaoTalk_Photo_2021-06-17-15-40-01.jpeg?table=block&id=f00bb3ea-edd4-4d34-b34b-0a9dfd224ec0&spaceId=e3c880b6-d001-4d49-9fa8-eec8e2ff00a1&width=710&userId=&cache=v2"
          img-alt="Image"
          img-top
        >
          <b-button
            href="https://kwangkyun-world.tistory.com/"
            variant="primary"
            >블로그 방문</b-button
          >
        </b-card>
      </b-col>
      <b-col cols="6">
        <b-card
          class="dev_img"
          title="박현우"
          img-src="https://treasure-isthmus-e6a.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F1c98e5e4-7c9c-4ac8-b12a-9ccd2be56b80%2F박현우.jpg?table=block&id=bd65f128-08c7-4c4b-921d-956d7afa30fe&spaceId=e3c880b6-d001-4d49-9fa8-eec8e2ff00a1&width=830&userId=&cache=v2"
          height="300px"
          img-alt="Image"
          img-top
        >
          <b-button href="#" variant="primary">블로그 방문</b-button>
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

<style>
.dev_img {
  width: 380px;
  height: 270px;
}
</style>
