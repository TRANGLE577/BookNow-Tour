const { authJwt } = require("../middleware");
const controller = require("../controllers/location.controller");

module.exports = function (app) {
  app.use(function (req, res, next) {
    res.header(
      "Access-Control-Allow-Headers",
      "x-access-token, Origin, Content-Type, Accept"
    );
    next();
  });

  app.get(
    "/api/locations",
    [authJwt.verifyToken],
    controller.getAll
  );

  app.get(
    "/api/locations/id/:id",
    [authJwt.verifyToken],
    controller.getById
  );
};