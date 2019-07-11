import React from 'react';
import { connect } from 'react-redux';

export interface IOrderSumaryProp extends StateProps, DispatchProps {}

export class OrderSumary extends React.Component<IOrderSumaryProp> {
  render() {
    return (
      <>
        <h4>Tóm tắt thông tin đơn hàng</h4>
        <ul className="list-group clear-list m-t">Chưa có thông tin</ul>
      </>
    );
  }
}

const mapStateToProps = storeState => ({});

const mapDispatchToProps = {};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderSumary);
