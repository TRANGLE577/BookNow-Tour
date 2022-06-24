const { authJwt } = require("../middleware");
const controller = require("../controllers/tour.controller");

module.exports = function (app) {
  app.use(function (req, res, next) {
    res.header(
      "Access-Control-Allow-Headers",
      "x-access-token, Origin, Content-Type, Accept"
    );
    next();
  });

  app.get(
    "/api/tours",
    // [authJwt.verifyToken],
    controller.getAll
  );

  app.get(
    "/api/tours/random",
    // [authJwt.verifyToken],
    controller.getByRandom
  );

  app.get(
    "/api/tours/search",
    // [authJwt.verifyToken],
    controller.getBySearch
  );

  app.get(
    "/api/tours/id/:id",
    // [authJwt.verifyToken],
    controller.getById
  );

  app.post(
      "/api/tours/save",
      [authJwt.verifyToken],
      controller.uploadImg,
      controller.save
  );

  app.post(
      "/api/tours/getReportTour",
      [authJwt.verifyToken],
      controller.getReportTour
  );

  app.post(
      "/api/tours/deleteTour",
      [authJwt.verifyToken],
      controller.deleteTour
  );
  
};