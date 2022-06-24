const { authJwt } = require("../middleware");
const controller = require("../controllers/account.controller");

module.exports = function (app) {
  app.use(function (req, res, next) {
    res.header(
      "Access-Control-Allow-Headers",
      "x-access-token, Origin, Content-Type, Accept"
    );
    next();
  });

  app.get(
    "/api/accounts",
    [authJwt.verifyToken, authJwt.isAdmin],
    controller.getAll
  );

  app.get(
    "/api/accounts/id/:id",
    [authJwt.verifyToken],
    controller.getById
  );

  app.post(
      "/api/accounts/deleteAccount",
      [authJwt.verifyToken],
      controller.deleteAccount
  );

};