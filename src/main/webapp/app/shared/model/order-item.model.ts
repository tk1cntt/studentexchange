import { Moment } from 'moment';

export interface IOrderItem {
  id?: number;
  itemId?: string;
  itemImage?: string;
  itemName?: string;
  itemLink?: string;
  itemPrice?: number;
  itemPriceNDT?: number;
  itemNote?: string;
  propertiesId?: string;
  propertiesImage?: string;
  propertiesMD5?: string;
  propertiesName?: string;
  propertiesType?: string;
  quantity?: number;
  requireMin?: number;
  totalAmount?: number;
  totalAmountNDT?: number;
  createAt?: Moment;
  updateAt?: Moment;
  orderCartId?: number;
  createByLogin?: string;
  createById?: number;
  updateByLogin?: string;
  updateById?: number;
}

export const defaultValue: Readonly<IOrderItem> = {};
