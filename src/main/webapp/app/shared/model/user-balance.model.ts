import { Moment } from 'moment';

export interface IUserBalance {
  id?: number;
  balanceAvailable?: number;
  balanceFreezing?: number;
  cash?: number;
  createAt?: Moment;
  updateAt?: Moment;
  createByLogin?: string;
  createById?: number;
}

export const defaultValue: Readonly<IUserBalance> = {};
