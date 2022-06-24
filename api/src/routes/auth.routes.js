const { verifySignUp, authJwt } = require("../middleware");
const controller = require("../controllers/auth.controller");

module.exports = function (app) {
  app.use(function (req, res, next) {
    res.header(
      "Access-Control-Allow-Headers",
      "x-access-token, Origin, Content-Type, Accept"
    );
    next();
  });

  app.post(
    "/api/auth/register",
    [
      verifySignUp.checkDuplicateUsernameOrEmail,
      verifySignUp.checkRolesExisted
    ],
    controller.register
  );

  app.post(
    "/api/auth/register/v2",
    [
      verifySignUp.checkDuplicateUsernameOrEmailV2,
      verifySignUp.checkRolesExisted
    ],
    controller.registerV2
  );

  app.post(
    "/api/auth/save",
    [authJwt.verifyToken],
    controller.save
  );

  app.post("/api/auth/login", controller.login);
};