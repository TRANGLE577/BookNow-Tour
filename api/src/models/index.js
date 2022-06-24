const config = require("../configs/db.config.js");

const Sequelize = require("sequelize");
const sequelize = new Sequelize(
  config.DB,
  config.USER,
  config.PASSWORD,
  {
    host: config.HOST,
    dialect: config.dialect,

    pool: {
      max: config.pool.max,
      min: config.pool.min,
      acquire: config.pool.acquire,
      idle: config.pool.idle
    }
  }
);

const db = {};

db.Sequelize = Sequelize;
db.sequelize = sequelize;

db.Account = require("../models/account.model.js")(sequelize, Sequelize);
db.Role = require("../models/role.model.js")(sequelize, Sequelize);
db.Location = require("../models/location.model.js")(sequelize, Sequelize);
db.Ticket = require("../models/ticket.model.js")(sequelize, Sequelize);
db.Tour = require("../models/tour.model.js")(sequelize, Sequelize);
db.Location = require("../models/location.model.js")(sequelize, Sequelize);
db.Payment = require("../models/payment.model.js")(sequelize, Sequelize);
db.Booking = require("../models/booking.model.js")(sequelize, Sequelize);

// mapping account and role
db.Role.hasOne(db.Account, {
  foreignKey: "roleId",
});
db.Account.belongsTo(db.Role, {
  foreignKey: "roleId",
  as: "role",
});

// mapping tour and location
db.Location.hasOne(db.Tour, {
    foreignKey: "locationId",
});
db.Tour.belongsTo(db.Location, {
    foreignKey: "locationId",
    as: "location",
});

// mapping booking and tour
db.Tour.hasOne(db.Booking, {
  foreignKey: "tourId",
});
db.Booking.belongsTo(db.Tour, {
  foreignKey: "tourId",
  as: "tour",
});

// mapping booking and payment
db.Payment.hasOne(db.Booking, {
  foreignKey: "paymentId",
});
db.Booking.belongsTo(db.Payment, {
  foreignKey: "paymentId",
  as: "payment",
});

// mapping booking and account
db.Account.hasOne(db.Booking, {
  foreignKey: "accountId",
});
db.Booking.belongsTo(db.Account, {
  foreignKey: "accountId",
  as: "account",
});

module.exports = db;