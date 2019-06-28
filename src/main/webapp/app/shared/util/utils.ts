/* tslint:disable */
const Hashids = require('hashids').default;
const hashids = new Hashids('id.dropshipping.com.vn');

export const formatMoney = money => {
  let moneyFormat = '';
  if (money >= 1000000000) {
    moneyFormat += humanize(money / 1000000000);
    moneyFormat += ' tỷ';
  } else if (1000000 <= money && money < 1000000000) {
    moneyFormat += humanize(money / 1000000);
    moneyFormat += ' triệu';
  } else if (1000 <= money && money < 1000000) {
    moneyFormat += humanize(money / 1000);
    moneyFormat += ' ngàn';
  } else {
    moneyFormat += humanize(money);
    moneyFormat += ' ngàn';
  }
  return moneyFormat;
};

export const formatCurency = money => {
  return money ? money.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.') : 0;
};

export const humanize = x => {
  return x ? x.toFixed(2).replace(/\.?0*$/, '') : x;
};

export const encodeId = id => hashids.encode(id, 20190101);

export const decodeId = id => hashids.decode(id)[0];

export const paymentQueryStringMapping = parameters => {
  let queryString = '';
  queryString += parameters.paymentStatus ? 'paymentStatus.equals=' + parameters.paymentStatus + '&' : '';
  queryString += parameters.code ? 'code.contains=' + parameters.code + '&' : '';
  return queryString.slice(0, -1);
};

export const queryString = parameters => {
  let queryString = '';
  for (const key in parameters) {
    if (parameters[key]) {
      queryString += key + '=' + parameters[key] + '&';
    }
  }
  return queryString.slice(0, -1);
};

export const stringToSlug = str => {
  str = str.toLowerCase();
  str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, 'a');
  str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, 'e');
  str = str.replace(/ì|í|ị|ỉ|ĩ/g, 'i');
  str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, 'o');
  str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, 'u');
  str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, 'y');
  str = str.replace(/đ/g, 'd');
  str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g, '');
  // str= str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g,"-");

  str = str.replace(/-+-/g, ''); //thay thế 2- thành 1-
  // str= str.replace(/-+-/g,"-"); //thay thế 2- thành 1-
  str = str.replace(/^\-+|\-+$/g, '');

  return str;
};
