const boardStore = {
  namespaced: true,
  state: {
    comments: [],
  },
  getters: {
    comments(state) {
      console.log(state);
      return state.comments;
    },
  },
  mutations: {
    SET_COMMENTS(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    getComments({ commit }, payload) {
      http
        .get("/comment/" + payload)
        .then(({ data }) => {
          console.log(data);
          commit("SET_COMMENTS", data);
        })
        .catch(() => {
          alert("서평조회 : 에러 발생!");
        });
    },
  },
};

export default boardStore;
