import { Moment } from 'moment';

export interface IUserShippingAddress {
  id?: number;
  name?: string;
  address?: string;
  mobile?: string;
  note?: string;
  createAt?: Moment;
  updateAt?: Moment;
  userProfileId?: number;
  createByLogin?: string;
  createById?: number;
  updateByLogin?: string;
  updateById?: number;
  cityId?: number;
  districtId?: number;
}

export const defaultValue: Readonly<IUserShippingAddress> = {};
