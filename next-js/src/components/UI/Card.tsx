import { ReactNode } from 'react';
import classes from './Card.module.css';

const Card = ({children}: {children: ReactNode}) => {
    return <div className={classes.card}>
        {children}
    </div>
}

export default Card;