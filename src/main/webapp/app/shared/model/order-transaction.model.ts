import { Moment } from 'moment';

export const enum OrderTransactionType {
  DEPOSIT = 'DEPOSIT',
  ORDER_PAYMENT = 'ORDER_PAYMENT',
  REFUND = 'REFUND'
}

export interface IOrderTransaction {
  id?: number;
  amount?: number;
  note?: string;
  status?: OrderTransactionType;
  createAt?: Moment;
  orderCartId?: number;
  createByLogin?: string;
  createById?: number;
}

export const defaultValue: Readonly<IOrderTransaction> = {};
