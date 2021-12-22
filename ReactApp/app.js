import CurrencyRatioTransformer from "./CurrencyRatioTransformer";
import Transformer from "./data/Transformer"

const React = require('react');
const ReactDOM = require('react-dom');


class App extends React.Component {

    constructor(props) {
        super(props);
        let transformers = [
            new Transformer(2, 0.0, 0.0, 0.00003),
            new Transformer(4, 0.0, 0.0, 0.00003),
            new Transformer(6, 0.0, 0.0, 0.00003),
            new Transformer(8, 0.0, 0.0, 0.00003),
            new Transformer(10, 0.0, 0.0, 0.00003),
            new Transformer(13, 0.0, 0.0, 0.00003),
            new Transformer(22, 0.0, 0.0, 0.00003),
            new Transformer(183, 0.0, 0.0, 0.00003),
        ]
        this.state = {transformers: transformers, autoUpdate: false};
        this.setAutoUpdate = this.setAutoUpdate.bind(this);
    }

    setAutoUpdate() {
        this.setState(
            {
                autoUpdate: !this.state.autoUpdate
            }
        );
        console.log(this.state.autoUpdate ?"auto-update enabled":"auto-update disabled")
    }

    render() {
        return (
            <div>
                <div>
                    <label>auto update</label>
                    <input type="checkbox"
                           checked={this.state.autoUpdate}
                           onChange={this.setAutoUpdate}/>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th>time</th>
                        <th>Pool ID</th>
                        <th>Previous ratio</th>
                        <th>current ratio</th>
                        <th>Gap</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.transformers.map(tr => <CurrencyRatioTransformer transformer={tr} autoUpdate={this.state.autoUpdate}/>)}
                    </tbody>
                </table>
            </div>


        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
)