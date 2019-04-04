package mx.models

case class RealEstate(
                     mls: Int,
                     location: String,
                     price: Double,
                     bedrooms: Int,
                     bathrooms: Int,
                     size: Int,
                     price_sq_ft: Double,
                     status: String
                     )