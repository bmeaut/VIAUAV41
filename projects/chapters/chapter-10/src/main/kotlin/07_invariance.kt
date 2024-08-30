package invariance

import contravariance.Collector
import covariance.Dispenser
import variance.Snack

interface Box<T : Snack> : Dispenser<T>, Collector<T>
