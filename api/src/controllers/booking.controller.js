const bookingService = require('../services/booking.service');

// route functions

function getAll(req, res, next) {
  bookingService.getAll()
      .then(data => res.json(data))
      .catch(next);
}

function getById(req, res, next) {
  bookingService.getById(req.params.id)
      .then(data => res.json(data))
      .catch(next);
}

function save(req, res, next) {
  bookingService.save(req, res)
      .then(data => res.json(data))
      .catch(next);
};

function getReportBooking(req, res, next) {
  bookingService.getReportBooking(req, res)
      .then(data => res.json(data))
      .catch(next);
}

function cancle(req, res, next) {
  bookingService.cancle(req, res)
      .then(data => res.json(data))
      .catch(next);
};

function inprogress(req, res, next) {
  bookingService.getAllBookingInProgess(req, res)
      .then(data => res.json(data))
      .catch(next);
};

function getAllBookingByTour(req, res, next) {
  bookingService.getAllBookingByTour(req, res)
      .then(data => res.json(data))
      .catch(next);
};

module.exports = {
  getAll,
  getById,
  save,
  getReportBooking,
  cancle,
  inprogress,
  getAllBookingByTour,
};

