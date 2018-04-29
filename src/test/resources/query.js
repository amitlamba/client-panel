/*(where name==Added to cart  and
( attributes.Category Contains [hello] or attributes.Category Contains [world] )  and
creationTime Before [2018-04-28T18:30:00.000Z]  and
sumof() Equals [25]) and (where name==App Uninstalled  and
( count of event == 1 )  and
creationTime Before [2018-04-29]  and
count Equals [2]) and
 ( not (where name==Added to cart  and
( attributes.Product DoesNotContain [test] or attributes.Product Contains [hello] )  and
creationTime Before [2018-04-01T18:30:00.000Z]) )

and (UserProperties.User Property Name Contains [25] or UserProperties.User Property Name Contains [40])
 and (Demographics.age NotEquals [25-35])
 and (Technographics.Browser Contains [Chrome] or Technographics.Browser Contains [Firefox])
 and (Technographics.Device Equals [Mobile] or Technographics.Device Contains [Tablet])
 and (AppFields.OS Version Equals [9.1])
 */
