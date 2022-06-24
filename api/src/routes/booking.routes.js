const { authJwt } = require("../middleware");
const controller = require("../controllers/booking.controller");

module.exports = function (app) {
  app.use(function (req, res, next) {
    res.header(
      "Access-Control-Allow-Headers",
      "x-access-token, Origin, Content-Type, Accept"
    );
    next();
  });

  app.get(
    "/api/bookings",
    [authJwt.verifyToken],
    controller.getAll
  );

  app.get(
    "/api/bookings/id/:id",
    [authJwt.verifyToken],
    controller.getById
  );

  app.post(
      "/api/bookings/save",
      [authJwt.verifyToken],
      controller.save
  );

  app.post(
      "/api/bookings/getReportBooking",
      [authJwt.verifyToken],
      controller.getReportBooking
  );
  
  app.post(
    "/api/bookings/cancle",
    [authJwt.verifyToken],
    controller.cancle
);

  app.get(
      "/api/bookings/inprogress",
      [authJwt.verifyToken],
      controller.inprogress
  );

  app.post(
      "/api/bookings/getAllBookingByTour",
      [authJwt.verifyToken],
      controller.getAllBookingByTour
  );

};