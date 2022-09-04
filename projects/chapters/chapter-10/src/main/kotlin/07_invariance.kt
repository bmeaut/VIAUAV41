package invariance

import contravariance.HandoffPoint
import covariance.PickupPoint
import variance.Car

interface Garage<T : Car> : PickupPoint<T>, HandoffPoint<T>
