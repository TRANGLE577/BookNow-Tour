const db = require("../models");
const Account = db.Account;

checkDuplicateUsernameOrEmail = (req, res, next) => {
  // Username
  Account.findOne({
    where: {
      username: req.body.username
    }
  }).then(user => {
    if (user) {
      res.status(400).send({
        message: "Failed! Username is already in use!"
      });
      return;
    }

    // Email
    Account.findOne({
      where: {
        email: req.body.email
      }
    }).then(user => {
      if (user) {
        res.status(400).send({
          message: "Failed! Email is already in use!"
        });
        return;
      }

      next();
    });
  });
};

checkDuplicateUsernameOrEmailV2 = (req, res, next) => {
  // Username
  Account.findOne({
    where: {
      username: req.body.username
    }
  }).then(user => {
    if (user) {
      res.status(200).send({
        code: 'error',
        message: "Failed! Username is already in use!",
        account: {},
      });
      return;
    }

    // Email
    Account.findOne({
      where: {
        email: req.body.email
      }
    }).then(user => {
      if (user) {
        res.status(200).send({
          code: 'error',
          message: "Failed! Email is already in use!",
          account: {},
        });
        return;
      }

      next();
    });
  });
};

checkRolesExisted = (req, res, next) => {
  if (req.body.roles) {
    for (let i = 0; i < req.body.roles.length; i++) {
      if (!ROLES.includes(req.body.roles[i])) {
        res.status(400).send({
          message: "Failed! Role does not exist = " + req.body.roles[i]
        });
        return;
      }
    }
  }
  
  next();
};

const verifySignUp = {
  checkDuplicateUsernameOrEmail: checkDuplicateUsernameOrEmail,
  checkDuplicateUsernameOrEmailV2: checkDuplicateUsernameOrEmailV2,
  checkRolesExisted: checkRolesExisted
};

module.exports = verifySignUp;