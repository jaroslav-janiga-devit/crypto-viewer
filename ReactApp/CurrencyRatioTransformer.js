const React = require('react');

export default class CurrencyRatioTransformer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {transformer: props.transformer,autoUpdate:props.autoUpdate};
        this.reloadPool = this.reloadPool.bind(this);
        this.gapReached = this.gapReached.bind(this);
    }

    componentDidUpdate() {
        if (this.state.autoUpdate && this.interval == null) {
            this.interval = setInterval(() => this.reloadPool(), 10000)
        }
    }
    componentDidMount() {
        if (this.state.autoUpdate && this.interval == null) {
            this.interval = setInterval(() => this.reloadPool(), 10000)
        } else {
            this.reloadPool();
        }
    }

    componentWillUnmount() {
        if (this.state.autoUpdate) {
            clearInterval(this.interval)
        }
    }

    static getDerivedStateFromProps(props,state){
        if(props.autoUpdate !== state.autoUpdate){
            return {autoUpdate:props.autoUpdate}
        }
        return null;
    }

    render() {
        const transformer = this.state.transformer;
        return (
            <tr style={{backgroundColor: this.gapReached() ? 'green' : 'white'}}>
                <td> {this.state.time} </td>
                <td> {transformer.pool} </td>
                <td> {transformer.previousRatio} </td>
                <td> {transformer.currentRatio} </td>
                <td> {transformer.gap} </td>
                <td>
                    <button onClick={this.reloadPool}>reload</button>
                </td>
                <td>{this.state.autoUpdate? "yes":"no"}</td>
            </tr>

        )
    }

    reloadPool() {
        console.log("reload")
        fetch("http://localhost:8081/api/pool/" + this.props.transformer.pool)
            .then(response => response.json())
            .then(
                data => {
                    let transformer = this.state.transformer;
                    transformer.previousRatio = transformer.currentRatio
                    transformer.currentRatio = data.ratio
                    this.setState({transformer: transformer})
                },
                (error) => console.log(error)
            )
    }

    gapReached() {
        return (this.state.transformer.currentRatio - this.state.transformer.previousRatio) >= this.state.transformer.gap;
    }
}
