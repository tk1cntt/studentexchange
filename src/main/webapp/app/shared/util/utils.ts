/* tslint:disable */
const Hashids = require('hashids').default;
const hashids = new Hashids('id.dropshipping.com.vn');

export const humanize = x => {
  return x ? x.toFixed(2).replace(/\.?0*$/, '') : x;
};

export const encodeId = id => hashids.encode(id, 20190101);

export const decodeId = id => hashids.decode(id)[0];
