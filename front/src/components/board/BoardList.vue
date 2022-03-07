<template>
  <!-- <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
             하위 component인 ListRow에 데이터 전달(props) 
            <board-list-row
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> 
    </b-row>
  </b-container> -->

  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>조회수</th>
          <th>작성자</th>
          <th>작성일</th>
          <th><v-btn @click="moveWrite()">글쓰기</v-btn></th>
        </tr>
      </thead>
      <tbody>
        <board-list-row
          style="height: 100px; font-size: 30px; text-align: left"
          v-for="(article, index) in articles"
          :key="index"
          v-bind="article"
        />
        <infinite-loading @infinite="infiniteHandler">
          <div
            slot="no-more"
            style="
              color: rgb(102, 102, 102);
              font-size: 14px;
              padding: 10px 0px;
            "
          >
            더 이상 목록이 없습니다.
          </div>
        </infinite-loading>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import BoardListRow from "@/components/board/child/BoardListRow";
import { listArticle } from "@/api/board.js";
import InfiniteLoading from "vue-infinite-loading";
// import { apiInstance } from "./index.js";

// const api = apiInstance();
export default {
  name: "BoardList",
  components: {
    BoardListRow,
    InfiniteLoading,
  },
  data() {
    return {
      articles: [],
      pg: 2,
      list: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 10,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "BoardWrite" });
    },
    infiniteHandler($state) {
      listArticle(
        {
          pg: this.pg,
          spp: this.spp,
        },
        (response) => {
          console.log(response.data);
          if (response.data.length) {
            this.page += 1;
            this.spp += 10;
            this.articles.push(...response.data);
            $state.loaded();
          } else {
            $state.complete();
          }
        },
        (error) => {
          console.log(error);
        }
      );
      // axios
      //   .get(api, {
      //     params: {
      //       pg: this.pg,
      //     },
      //   })
      //   .then(({ data }) => {
      //     console.log(data);
      //     if (data.hits.length) {
      //       this.page += 1;
      //       this.articles.push(...data.hits);
      //       $state.loaded();
      //     } else {
      //       $state.complete();
      //     }
      //   });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 80px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
