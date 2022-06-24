const userService = require('../services/user.service');

// route functions

function getBooking(req, res, next) {
  userService.getBooking(req.params.id)
      .then(data => res.json(data))
      .catch(next);
}

function getHistory(req, res, next) {
  userService.getHistory(req.params.id)
      .then(data => res.json(data))
      .catch(next);
}

module.exports = {
  getBooking,
  getHistory,
};
