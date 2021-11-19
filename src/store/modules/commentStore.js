import { commentList } from "@/api/comment.js";
const commentStore = {
  namespaced: true,
  state: {
    comments: [],
  },
  getters: {
    comments(state) {
      return state.comments;
    },
  },
  mutations: {
    SET_COMMENTS(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    getComments: ({ commit }, articleno) => {
      commentList(
        articleno,
        ({ data }) => {
          console.log(commit);
          commit("SET_COMMENTS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default commentStore;
